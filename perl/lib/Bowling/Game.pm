package Bowling::Game;
use v5.14;
use warnings;

use List::Util qw(sum);

sub new {
    my ($class) = @_;

    my $self = bless {
        rolls => [],
        current_role => 0,
    }, $class;

    return $self;
}

sub role {
    my ($self, $pins) = @_;
    $self->{rolls}->[$self->{current_role}] = $pins;
    $self->{current_role} += 1;
}

sub score {
    my ($self) = @_;
    my $score = 0;
    my $frame_index = 0;
    for (0..9) {
        if ( $self->is_strike($frame_index) ) {
            $score += 10 + $self->strike_bonus($frame_index);
            $frame_index += 1;
        } elsif ( $self->is_spare($frame_index) ) {
            $score += 10 + $self->spare_bonus($frame_index);
            $frame_index += 2;
        } else {
            $score += $self->sum_of_pins_in_frame($frame_index);
            $frame_index += 2;
        }
    }
    return $score;
}

sub pins_of_roll {
    my ($self, $roll_n) = @_;
    return $self->{rolls}->[$roll_n];
}

sub is_strike {
    my ($self, $frame_index) = @_;
    return $self->pins_of_roll($frame_index) == 10;
}

sub is_spare {
    my ($self, $frame_index) = @_;
    return
        (  $self->pins_of_roll($frame_index)
         + $self->pins_of_roll($frame_index + 1) ) == 10;
}

sub sum_of_pins_in_frame {
    my ($self, $frame_index) = @_;
    return $self->pins_of_roll($frame_index)
         + $self->pins_of_roll($frame_index + 1);
}

sub strike_bonus {
    my ($self, $frame_index) = @_;

    return $self->pins_of_roll($frame_index + 1)
         + $self->pins_of_roll($frame_index + 2);
}

sub spare_bonus {
    my ($self, $frame_index) = @_;

    return $self->pins_of_roll($frame_index + 2)
}

1;
