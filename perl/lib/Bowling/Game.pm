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
    return sum map {
        my $frame_n = $_;

        $self->is_spare($frame_n) ?
            10 + $self->pins_of_roll($frame_n * 2 + 2) :
            (  $self->pins_of_roll($frame_n * 2)
             + $self->pins_of_roll($frame_n * 2 + 1) );
    } (0..9)
}

sub is_spare {
    my ($self, $frame_n) = @_;
    return
        (  $self->pins_of_roll($frame_n * 2)
         + $self->pins_of_roll($frame_n * 2 + 1) ) == 10;
}

sub pins_of_roll {
    my ($self, $roll_n) = @_;
    return $self->{rolls}->[$roll_n];
}

1;
