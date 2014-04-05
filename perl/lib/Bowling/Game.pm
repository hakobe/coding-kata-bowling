package Bowling::Game;
use v5.14;
use warnings;

sub new {
    my ($class) = @_;

    my $self = bless {
    }, $class;

    return $self;
}

sub role {
    my ($self) = @_;
}

sub score {
    return 0
}

1;
